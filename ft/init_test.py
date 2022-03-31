#!env python3

from ft_config import *


def test_init_env():
  """reset database"""
  os.system(f"mysql -u{mysql_user} -p{mysql_passwd} < tables.sql")


def test_register_alice():
  public, private = get_rsa_key_pair()

  url = "http://localhost:8080/register"

  payload = json.dumps({
      "username": "alice",
      "password": sha256(password["alice"]),
      "publicKey": public,
      "privateKey": aes_encrypt(private, password["alice"]),
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
  userIds["alice"] = get_uid("alice")


def test_register_bob():
  public, private = get_rsa_key_pair()

  url = "http://localhost:8080/register"

  payload = json.dumps({
      "username": "bob",
      "password": sha256(password["bob"]),
      "publicKey": public,
      "privateKey": aes_encrypt(private, password["bob"]),
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
  userIds["bob"] = get_uid("bob")
