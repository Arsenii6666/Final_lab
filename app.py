from flask import Flask
import time

app = Flask(__name__)

@app.route("/")
def hello():
    time.sleep(1000)
    return "hello"

if __name__ == "__main__":
    app.run(debug=False, port=8080)