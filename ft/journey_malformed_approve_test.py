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


def test_login_bob():
  url = "http://localhost:8080/login"

  payload = json.dumps({
      "username": "bob",
      "password": sha256(password["bob"]),
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
  tokens["bob"] = data["token"]


def test_create_journey_alice():
  url = "http://localhost:8080/journey/create"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "from": {
              "latitude": -18077544.679667488,
              "longitude": -75077956.63059595
          },
          "to": {
              "latitude": -66335255.51463202,
              "longitude": 63560453.682985514
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

  assert data["success"] == True, data["reason"]
  journeys["alice"] = data["payload"]["id"]


def test_join_journey_bob():
  url = "http://localhost:8080/journey/join"

  payload = json.dumps({
      "token": tokens["bob"],
      "timestamp": timestamp(),
      "payload": {
          "journeyId": journeys["alice"]
      }
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]


def test_approve_join_bob():
  url = "http://localhost:8080/journey/get-unapproved"

  payload = json.dumps({
      "token": tokens["bob"],
      "timestamp": timestamp()
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  for userId in data["payload"]["userIds"]:
    url = "http://localhost:8080/journey/approve-join"

    payload = json.dumps({
        "token": tokens["alice"],
        "timestamp": timestamp(),
        "payload": {
            "userId": userId
        }
    })
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
    }

    response = requests.request("POST", url, headers=headers, data=payload)
    print(response.text)
    data = json.loads(response.text)

    assert data["success"] == False
