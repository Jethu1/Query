package query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by jet on 2017/7/28.
 */
public class Index {
    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.open(new File("index"));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
        IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LUCENE_46, analyzer);
        writerConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter = new IndexWriter(directory, writerConfig);


        Document doc = new Document();
        Document doc2 = new Document();
        Document doc3 = new Document();
        Document doc4 = new Document();
        String text0 ="apple other other other boy";
        String text1 ="apple apple other other other";
        String text2 ="apple apple apple other other";
        String text3 ="apple apple apple apple other";

        doc.add(new TimeField("teacherid", "1", Field.Store.YES));
        doc.add(new TimeField("studentfirstname", "james", Field.Store.YES));
        doc.add(new TimeField("studentsurname", "jones", Field.Store.YES));
        doc2.add(new TimeField("teacherid", "2", Field.Store.YES));
        doc2.add(new TimeField("studentfirstname", "james", Field.Store.YES));
        doc2.add(new TimeField("studentsurname", "smith", Field.Store.YES));
        doc2.add(new TimeField("studentfirstname", "sally", Field.Store.YES));
        doc2.add(new TimeField("studentsurname", "jones", Field.Store.YES));
//        doc1.add(new TimeField("Contents", text0, Field.Store.YES));
//        doc1.add(new Field("scorefield","10",Field.Store.NO,Field.Index.NOT_ANALYZED));
//        doc2.add(new TimeField("Contents", text1, Field.Store.YES));
//        doc2.add(new Field("scorefield","1",Field.Store.NO,Field.Index.NOT_ANALYZED));
//        doc3.add(new TimeField("Contents", text2, Field.Store.YES));
//        doc3.add(new Field("scorefield","1",Field.Store.NO,Field.Index.NOT_ANALYZED));
//        doc4.add(new TimeField("Contents", text3, Field.Store.YES));
//        doc4.add(new Field("scorefield","1",Field.Store.NO,Field.Index.NOT_ANALYZED));
        indexWriter.addDocument(doc);
        indexWriter.addDocument(doc2);
//        indexWriter.addDocument(doc2);
//        indexWriter.addDocument(doc3);
//        indexWriter.addDocument(doc4);
        indexWriter.close();
    }
}
