package query;

import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;

/**
 * Created by jet on 2017/7/29.
 */
public class RangeQuery {

    public static void main(String[] args) {
        String field = "starttime";
        BytesRef byte1 = new BytesRef("asds".getBytes(),0,5);
        BytesRef byte2 = new BytesRef("asds".getBytes(),0,5);
        TermRangeQuery query = new TermRangeQuery(field,byte1,byte2,true,true);
        NumericRangeQuery<Integer> query1 = NumericRangeQuery.newIntRange("id", 3, 6, true, false);
    }
}
