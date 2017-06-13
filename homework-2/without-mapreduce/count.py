import sys
import collections

search_strings = ['hackathon', 'Dec', 'Chicago', 'Java']
search_strings.sort()
search_strings_count = collections.Counter()

if len(sys.argv) < 2:
    print('please provide input file')
    quit()

input_file = sys.argv[1]
with open(input_file,'r') as handle:
    input = handle.readlines()

for tweet in input:
    for search_string in search_strings:
        if search_string.lower() in tweet.lower():
            search_strings_count[search_string] += 1

for search_string in search_strings:
    print('{0} {1}'.format(search_string,search_strings_count[search_string]))