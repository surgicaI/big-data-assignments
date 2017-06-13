import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double pageRank = 0.0;
		StringBuilder reducerResult = new StringBuilder();
		
		for(Text value: values){
			String strVal = value.toString();
			if(!strVal.matches(".*\\d+.*")){
				reducerResult.append(strVal);
				reducerResult.append('\t');
			} else {
				pageRank += Double.parseDouble(strVal);	
			}	
		}
		
		reducerResult.append(String.valueOf(pageRank));
		context.write(key, new Text(reducerResult.toString()));
	}
}