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
  url = "http://localhost:8080/journey/get-by-location"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "from": {
              "longitude": -0.09998975,
              "latitude": 51.75436293
          },
          "to": {
              "longitude": -0.1337,
              "latitude": 51.5074
          }
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
