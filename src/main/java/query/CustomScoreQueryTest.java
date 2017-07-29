/*
package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.vectorhighlight.FieldQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

*/
/**
 * Created by jet on 2017/7/28.
 *//*

public class CustomScoreQueryTest {
    TermQuery subquery = new TermQuery(new Term("contents","apple"));
    FieldQuery scorefield = new FieldQuery("scorefield");
    CustomScoreQuery query = new CustomScoreQuery(subquery, scorefield);

    Directory directory = FSDirectory.open(new File("index"));


    //创建IndexReader
    DirectoryReader directoryReader  = DirectoryReader.open(directory);
    //根据IndexReader创建IndexSearch
    IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
    TopDocs topDocs = indexSearcher.search(query,10);
        System.out.println("查找到的文档总共有: " + topDocs.totalHits);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
        Document doc = indexSearcher.doc(scoreDoc.doc);// 根据文档的标识获取文档
        String time = doc.get("Contents");
        System.out.println("整篇文档的分数:"+scoreDoc.score+" 内容： "+time);
    }

    public CustomScoreQueryTest() throws IOException {
    }
}
*/
