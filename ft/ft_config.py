import json
import os
import subprocess
import time
import uuid

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
      f"mysql -u{mysql_user} -p{mysql_passwd} -Bse \"SELECT id FROM cs7cs3.user_info WHERE username = '{name}'\"", shell=True)
  uid = res.decode("utf-8")

  return uid.strip()


def rand_uuid():
  return str(uuid.uuid4())
