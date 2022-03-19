#!env python3

from ft_config import *


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
