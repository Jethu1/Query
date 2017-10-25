package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BooleanFilter;
import org.apache.lucene.queries.TermsFilter;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//        TermsFilter termsFilter = new TermsFilter();
        //spanNearquery  int slop; 设定这些字SpanQuery之间的距离的最大值，大于此值则文档不返回。
        SpanNearQuery[] spanNearQuerys = new SpanNearQuery[2];
        SpanTermQuery[] queries = new SpanTermQuery[3];
       queries[0] = new SpanTermQuery(new Term("content","本"));
       queries[1] = new SpanTermQuery(new Term("content","文"));
       queries[2] = new SpanTermQuery(new Term("content","为"));
        spanNearQuerys[1]= new SpanNearQuery(queries,0,true);
        SpanTermQuery[] queries1 = new SpanTermQuery[3];
        queries1[0] = new SpanTermQuery(new Term("content","作"));
        queries1[1] = new SpanTermQuery(new Term("content","为"));
        queries1[2] = new SpanTermQuery(new Term("content","一"));
        spanNearQuerys[0] = new SpanNearQuery(queries1,0,true);
        SpanNearQuery spanNearQuery = new SpanNearQuery(spanNearQuerys,20,true);

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

        /*
        TermsFilter   what the function of this class?
        Let's have a look.
         */

//        Term term = new Term("问");
        Term term1 = new Term("content","期");
        Term term2 = new Term("content","都");
        Term term3 = new Term("content","美");
        Term term4 = new Term("content","欧");
        Term term5 = new Term("content","韩");
        List<Term> list = new ArrayList<Term>();
//        list.add(term);
        list.add(term1);
        list.add(term2);
        list.add(term3);
        list.add(term4);
        list.add(term5);
        TermsFilter termsFilter1 = new TermsFilter(list);
        TermQuery termsQ = new TermQuery(new Term("content","美"));


        BooleanFilter booleanFilter = new BooleanFilter();
//        FilterClause clause = new FilterClause(termsFilter1, BooleanClause.Occur.MUST_NOT);
        booleanFilter.add(termsFilter1, BooleanClause.Occur.MUST_NOT);
        long start = System.currentTimeMillis();

        FilteredQuery query2 = new FilteredQuery(termsQ,termsFilter1);

        TopDocs docs = searcher.search(spanNearQuery,10);
//        TopDocs docs = searcher.search( queries[0],10);
        long end = System.currentTimeMillis();
        System.out.println("搜索所需时间： "+(end-start));
        System.out.println("总文档数： "+docs.totalHits);
       for (ScoreDoc doc : docs.scoreDocs) {
            Document ldoc = directoryReader.document(doc.doc);
            String title = ldoc.get("teacherid");
            System.out.println("分数： "+doc.score+" 内容： "+ldoc.get("content"));
        }

    }
}
