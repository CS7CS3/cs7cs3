#!env python3

from ft_config import *


def test_login_alice():
  url = "http://localhost:8080/login"

  payload = json.dumps({
      "username": "alice",
      "password": sha256(password["alice"]),
      "timestamp": timestamp()
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]
  tokens["alice"] = data["token"]


def test_join_journey_alice():
  url = "http://localhost:8080/journey/join"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "journeyId": rand_uuid()
      }
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  assert data["success"] == False
