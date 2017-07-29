package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BoostingQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Created by jet on 2017/7/28.
 */
/*
BoostingQuery最终生成一个BooleanQuery，第一项是match查询，是MUST，即required，
第二项是context查询，是SHOULD，即optional
然而由查询过程分析可得，即便是optional的查询，也会影响整个打分。
所以在BoostingQuery的构造函数中，设定context查询的boost为零，则无论文档是否包含context查询，
都不会影响最后的打分。
在rewrite函数中，重载了DefaultSimilarity的coord函数，当仅包含match查询的时候，其返回1，当既包含match查询，
又包含context查询的时候，返回boost，也即会在最后的打分中乘上boost的值。
 */

public class Boostquery {
    public static void main(String[] args) throws IOException {
        TermQuery query1 = new TermQuery(new Term("Contents","apple"));
        TermQuery query2 = new TermQuery(new Term("Contents","boy"));
        BoostingQuery query = new BoostingQuery(query1, query2, 3f);
        TermQuery query3 = new TermQuery(new Term("Contents","apple"));

//        MoreLikeThisQuery query = new MoreLikeThisQuery();


        Directory directory = FSDirectory.open(new File("index"));
        //创建IndexReader
        DirectoryReader directoryReader  = DirectoryReader.open(directory);
        //根据IndexReader创建IndexSearch
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        TopDocs topDocs = indexSearcher.search(query3,10);
        System.out.println("查找到的文档总共有: " + topDocs.totalHits);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);// 根据文档的标识获取文档
            String time = doc.get("Contents");
            System.out.println("整篇文档的分数:"+scoreDoc.score+" 内容： "+time);
        }
    }

}
