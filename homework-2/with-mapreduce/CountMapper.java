import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final String[] SEARCH_STRINGS = {"hackathon", "Dec", "Chicago", "Java"};
    private static final int COUNT = 1;
    private static final int COUNT_ZERO = 0;
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        for(String search_string:SEARCH_STRINGS){
            if(line.toLowerCase().contains(search_string.toLowerCase()))
                context.write(new Text(search_string), new IntWritable(COUNT));
            else
                context.write(new Text(search_string), new IntWritable(COUNT_ZERO));
        }
    }
}
