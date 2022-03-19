#!env python3

from ft_config import *


def test_login_alice():
  url = "http://localhost:8080/login"

  payload = json.dumps({
      "username": "alice",
      "password": "4mxeopYEdadsklDCJzHyK",
      "timestamp": timestamp()
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]
  userIds["alice"] = data["payload"]["userId"]
  tokens["alice"] = data["token"]


def test_login_bob():
  url = "http://localhost:8080/login"

  payload = json.dumps({
      "username": "bob",
      "password": "4mxeopYEdwhDCJzHyK",
      "timestamp": timestamp()
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]
  userIds["bob"] = data["payload"]["userId"]
  tokens["bob"] = data["token"]


def test_send_message_alice():
  url = "http://localhost:8080/message/send"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "receiver": userIds["bob"],
          "content": "hello,world"
      }
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]


def test_get_message_bob():
  url = "http://localhost:8080/message/get"

  payload = json.dumps({
      "token": tokens["bob"],
      "timestamp": timestamp(),
      "payload": {
          "from": 0,
          "len": 999999999
      }
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]
  assert data["payload"]["messages"][0]["content"] == "hello,world"
