import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split("\\s+");
        int length = tokens.length;
        double page_rank_old = Double.parseDouble(tokens[length - 1]);
        String page_rank_new = String.valueOf(page_rank_old / (length - 2));

        StringBuilder only_pages = new StringBuilder(tokens[0]);

        for(int i = 1; i < length - 1; i++){
            only_pages.append(" ");
            only_pages.append(tokens[i]);
            context.write(new Text(tokens[i]), new Text(page_rank_new));
        }
        context.write(new Text(tokens[0]), new Text(only_pages.toString()));
    }
}
