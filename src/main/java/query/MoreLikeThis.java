package query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queries.mlt.MoreLikeThisQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

/**
 * Created by jet on 2017/7/28.
 */

/*
在分析MoreLikeThisQuery之前，首先介绍一下MoreLikeThis。

在实现搜索应用的时候，时常会遇到"更多相似文章"，"更多相关问题"之类的需求，也即根据当前文档的文本内容，在索引库中查询相类似的文章。

我们可以使用MoreLikeThis实现此功能：

IndexReader reader = IndexReader.open(……);

IndexSearcher searcher = new IndexSearcher(reader);

MoreLikeThis mlt = new MoreLikeThis(reader);

Reader target = ... //此是一个io reader，指向当前文档的文本内容。

Query query = mlt.like( target); //根据当前的文本内容，生成查询对象。

Hits hits = searcher.search(query); //查询得到相似文档的结果。
 */

public class MoreLikeThis {
/*
    static Set<String> getStopWords(FileReader reader) throws IOException{

        HashSet<String> stop = new HashSet<String>();

        int numOfDocs = reader.numDocs();

        int stopThreshhold = (int) (numOfDocs*0.8f);

        TermsEnum te = reader;

        while(te.next()){

            String text = te.term().text();

            if(te.docFreq() >= stopThreshhold){

                stop.add(text);

            }

        }

        return stop;

    }*/

    public static void search() throws IOException {
        Directory directory = FSDirectory.open(new File("index"));
        //创建IndexReader
        DirectoryReader directoryReader  = DirectoryReader.open(directory);
        //根据IndexReader创建IndexSearch
        IndexSearcher searcher = new IndexSearcher(directoryReader);
        //将《IT外企那点儿事》作为likeText，从文件读入。
        StringBuffer contentBuffer = new StringBuffer();
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("morelike/it.txt"), "utf-8"));
        String line =null;
        while((line=input.readLine())!=null){
            contentBuffer.append(line);
        }
        String content = contentBuffer.toString();
        MoreLikeThisQuery query = new MoreLikeThisQuery(content,new String[]{"content"},new StandardAnalyzer(Version.LUCENE_46),"content");
        //将80%都包括的词作为停词，在实际应用中，可以有其他的停词策略。
//        query.setStopWords(getStopWords(directoryReader));
        query.setMinTermFrequency(5);
        query.setMaxQueryTerms(20);//
        TopDocs docs = searcher.search(query, 10);

        for (ScoreDoc doc : docs.scoreDocs) {

            Document ldoc = directoryReader.document(doc.doc);
            String title = ldoc.get("title");
            System.out.println(title);

        }

    }

    public static void index() throws IOException {
        Directory directory = FSDirectory.open(new File("index"));
        Analyzer timeAnalyzer = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_46, timeAnalyzer);
        writerConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, writerConfig);

        File file = new File("morelike");
        File[] dataFiles = file.listFiles();
        for (int i = 0; i <dataFiles.length ; i++) {
            if(!dataFiles[i].getName().equals("it.txt")&&dataFiles[i].getName().endsWith(".txt")){

                Document document = new Document();
                Reader txtReader = new FileReader(dataFiles[i]);
//                FileReader fr =  new FileReader ("c://in.txt");
                BufferedReader br = new BufferedReader (txtReader);

                String s;
                StringBuffer str=new StringBuffer();

                while ((s = br.readLine() )!=null) {
                    str.append(s);
                }
                System.out.println(" title: "+dataFiles[i].getName());
                System.out.println(" content: "+str.toString());
                Field field1 = new TimeField("title",dataFiles[i].getName(), Field.Store.YES);
                Field field2 = new TimeField("content",str.toString(), Field.Store.YES);
                FieldType type =  new FieldType();
                type.setStored(true);
                type.setTokenized(true);
                type.setIndexed(true);

                Field field3 = new Field("content",str.toString(),type);

                document.add(field1);
                document.add(field2);
                indexWriter.addDocument(document);

            }

        }
        indexWriter.close();
    }
}
