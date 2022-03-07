#!env python3

import json
import os
import subprocess
import time

import pytest
import requests

# ============= configs =============
mysql_user = "root"
mysql_passwd = "root"
# =========== end configs ===========


# ============= global =============
tokens = dict()
userIds = dict()
journeys = dict()
# =========== end global ===========


def timestamp():
  return int(time.time())


def get_uid(name):
  res = subprocess.check_output(
      f"mysql -uroot -proot --disable-column-names  -Be \"SELECT id FROM cs7cs3.user_info WHERE username = '{name}'\"", shell=True)
  uid = res.decode("utf-8")

  return uid.strip()


def test_init_env():
  """reset database"""
  os.system(f"mysql -u{mysql_user} -p{mysql_passwd} < tables.sql")


def test_register_alice():
  url = "http://localhost:8080/register"

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
  userIds["alice"] = get_uid("alice")


def test_register_bob():
  url = "http://localhost:8080/register"

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
  userIds["bob"] = get_uid("bob")


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
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
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
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]


def test_approve_join_alice():
  url = "http://localhost:8080/journey/get-unapproved"

  payload = json.dumps({
      "token": "d0d5ba42-ca57-4ab4-858c-19aed5a22e4b",
      "timestamp": timestamp()
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg=='
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  data = json.loads(response.text)

  for userId in data["payload"]["userIds"]:
    url = "http://localhost:8080/journey/approve-join"

    payload = json.dumps({
        "token": tokens["alice"],
        "timestamp": timestamp(),
        "payload": {
            "userId": "c61448b6-537b-5a93-f97a-b7364dc45106"
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


def main():
  pass


if __name__ == "__main__":
  main()
