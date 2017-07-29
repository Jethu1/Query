package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.TermsFilter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spans.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Created by jet on 2017/7/29.
 */
/*
    SpanQuery中最基本的是SpanTermQuery，其只包含一个Term，与TermQuery所不同的是，其提供一个函数来得到位置信息：
    next() 得到下一篇文档号，不同的SpanQuery此方法实现不同
    skipTo(int) 跳到指定的文档
    doc() 得到当前的文档号
    start() 得到起始位置，不同的SpanQuery此方法实现不同
    end() 得到结束位置，不同的SpanQuery此方法实现不同
    isPayloadAvailable() 是否有payload
    getPayload() 得到payload
 */

public class SpanQueryTest {
    public static void main(String[] args) throws IOException {
        SpanQueryTest test = new SpanQueryTest();
        test.search();
    }

    public static void search() throws IOException {
        Directory directory = FSDirectory.open(new File("index"));
        //创建IndexReader
        DirectoryReader directoryReader  = DirectoryReader.open(directory);
        //根据IndexReader创建IndexSearch
        IndexSearcher searcher = new IndexSearcher(directoryReader);
        //spanfirstquery。
        SpanTermQuery spanTermQuery = new SpanTermQuery(new Term("content","升"));
        SpanFirstQuery query = new SpanFirstQuery(spanTermQuery,14);

        TermsFilter termsFilter = new TermsFilter();
        //spanNearquery  int slop; 设定这些字SpanQuery之间的距离的最大值，大于此值则文档不返回。
        SpanTermQuery[] queries = new SpanTermQuery[3];
       queries[0] = new SpanTermQuery(new Term("content","问"));
       queries[1] = new SpanTermQuery(new Term("content","期"));
       queries[2] = new SpanTermQuery(new Term("content","划"));
        SpanNearQuery spanNearQuery = new SpanNearQuery(queries,3,false);

//SpanNotQuery包含如下两个成员变量：SpanQuery include; 必须满足的SpanQuery SpanQuery exclude; 必须不能满足的SpanQuery
//        其next函数从include中取出文档号，如果exclude也包括此文档号，则过滤掉。
// 如果exclude的end大于include的start，则说明当前文档号应该被exclude。
 //如果是因为没有exclude了，或者文档号不相同，或者include的end小于exclude的start，则当前文档不应该被exclude。
        SpanTermQuery spanTermQuery1 = new SpanTermQuery(new Term("content","一"));
        SpanTermQuery spanTermQuery2 = new SpanTermQuery(new Term("content","外"));
        SpanNotQuery notQuery = new SpanNotQuery(spanTermQuery1,spanTermQuery2);

//  SpanOrQuery 合并查询
        SpanOrQuery spanOrQuery = new SpanOrQuery(queries);

//FieldMaskingSpanQuery   在SpanNearQuery中，需要进行位置比较，相互比较位置的Term必须要在同一个域中，否则报异常
//"jones".end – "james".start – totallength = 1 – 0 – 2 = -1，这样就能够搜的出来。
        SpanQuery q1  = new SpanTermQuery(new Term("studentfirstname", "james"));
        SpanQuery q2  = new SpanTermQuery(new Term("studentsurname", "jones"));
        SpanQuery q2m = new FieldMaskingSpanQuery(q2, "studentfirstname");
        Query q3 = new SpanNearQuery(new SpanQuery[]{q1, q2m}, -1, false);
        TopDocs docs = searcher.search(q3,10);
        System.out.println("总文档数： "+docs.totalHits);
       for (ScoreDoc doc : docs.scoreDocs) {
            Document ldoc = directoryReader.document(doc.doc);
            String title = ldoc.get("teacherid");
            System.out.println("标题： "+title+" 内容： "+ldoc.get("content"));
        }

    }
}
