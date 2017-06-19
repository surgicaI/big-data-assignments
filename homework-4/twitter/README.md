## Big Data Homework-4

- Access Twitter by downloading and installing the appropriate library.
- Develop a program that gets recent tweets (either through the Streaming API or the REST API) and outputs them to
a file.

## Library used - [Tweepy](https://github.com/tweepy/tweepy) for python
- pip install tweepy

## Instructions
- Create an application [**here**](https://apps.twitter.com/) and note down `consumer key` and `consumer secret`.
- Generate Access token and note down `access token` and `access token secret`.
- Create a file `config.py` in same directory which will contain following info
```
consumer_key =  '**********'
consumer_secret = '******************************'
access_token = '**********-****************************************'
access_token_secret = '**************************************************'
```
- Command to execute the program:
```
python twitter.py
```
- Twitter data(in json format) will appended to file named `tweets-data` in the same directory. The file will be created if it doesn't exist already.
- In another file named `tweets-text` just the text of the tweets will be appended.