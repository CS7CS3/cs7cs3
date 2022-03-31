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
  privateKeys["alice"] = data["payload"]["privateKey"]
  userIds["alice"] = data["payload"]["userId"]
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
  privateKeys["bob"] = data["payload"]["privateKey"]
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
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
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
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  assert data["success"] == True, data["reason"]
  assert data["payload"]["messages"][0]["content"] == "hello,world"


def test_send_message_bob_secure():
  url = "http://localhost:8080/message/get-public-key"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "userId": userIds["alice"],
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
  publicKey = data["payload"]["publicKey"]

  url = "http://localhost:8080/message/send"

  payload = json.dumps({
      "token": tokens["bob"],
      "timestamp": timestamp(),
      "payload": {
          "receiver": userIds["alice"],
          "content": rsa_encrypt("hello,world", publicKey)
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


def test_get_message_alice_secure():
  url = "http://localhost:8080/message/get"

  payload = json.dumps({
      "token": tokens["alice"],
      "timestamp": timestamp(),
      "payload": {
          "from": 0,
          "len": 999999999
      }
  })
  headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
  }

  response = requests.request("POST", url, headers=headers, data=payload)
  print(response.text)
  data = json.loads(response.text)

  cipher = data["payload"]["messages"][1]["content"]
  privateKey = aes_decrypt(privateKeys["alice"], password["alice"])
  text = rsa_decrypt(cipher, privateKey)

  assert data["success"] == True, data["reason"]
  assert text == "hello,world"
