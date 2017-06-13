import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double page_rank = 0;
        String my_new_key = "";
        for (Text value : values) {
            try{
                String val = value.toString();
                page_rank += Double.parseDouble(val);
            } catch(NumberFormatException e){
                my_new_key = value.toString();
            }
        }
        String page_rank_string = String.valueOf(page_rank);
        context.write(new Text(my_new_key), new Text(page_rank_string));
    }
}
