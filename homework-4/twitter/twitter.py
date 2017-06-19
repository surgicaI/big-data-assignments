import tweepy
import config
import json


# override tweepy.StreamListener to add logic to on_status
class MyStreamListener(tweepy.StreamListener):
    def on_status(self, status):
        print(status.text)

    def on_data(self, data):
        with open('tweets-data', 'a') as handle:
            handle.write(data)
        tweet = json.loads(data)['text']
        with open('tweets-text', 'a') as handle:
            handle.write(tweet)
        return True

    def on_error(self, status_code):
        if status_code == 420:
            # returning False in on_data disconnects the stream
            return False


def main():
    auth = tweepy.OAuthHandler(config.consumer_key, config.consumer_secret)
    auth.set_access_token(config.access_token, config.access_token_secret)
    myStreamListener = MyStreamListener()
    myStream = tweepy.Stream(auth=auth, listener=myStreamListener)
    myStream.filter(track=['nyu', 'courant'])


if __name__ == '__main__':
    main()
