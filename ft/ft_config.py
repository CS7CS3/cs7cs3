import base64
import hashlib
import json
import os
import subprocess
import time
import uuid

import pyaes
import pytest
import requests
import rsa

# ============= configs =============
mysql_user = "root"
mysql_passwd = "root"
# =========== end configs ===========


# ============= global =============
tokens = dict()
password = {"alice": "4mxeopYEdadsklDCJzHyK", "bob": "4mxeopYEdwhDCJzHyK"}
privateKeys = dict()
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


def sha256(text):
  return hashlib.sha256(text.encode('utf-8')).hexdigest()


def get_rsa_key_pair():
  public, private = rsa.newkeys(2048)
  public_str = str(public.save_pkcs1(), encoding="utf-8")
  private_str = str(private.save_pkcs1(), encoding="utf-8")

  return public_str, private_str


def rsa_encrypt(text, public_key):
  public_key = rsa.PublicKey.load_pkcs1(public_key.encode("utf-8"))
  cipher = rsa.encrypt(text.encode("utf-8"), public_key)

  return str(base64.b64encode(cipher), encoding="utf-8")


def rsa_decrypt(cipher, private_key):
  cipher = base64.b64decode(cipher)

  private_key = rsa.PrivateKey.load_pkcs1(private_key.encode("utf-8"))
  text = rsa.decrypt(cipher, private_key)

  return str(text, encoding="utf-8")


def aes_encrypt(text, key):
  key = key.encode('utf-8') * 32

  aes = pyaes.AESModeOfOperationCTR(key[0:32])
  cipher = aes.encrypt(text)

  return str(base64.b64encode(cipher), encoding="utf-8")


def aes_decrypt(cipher, key):
  cipher = base64.b64decode(cipher)

  key = key.encode('utf-8') * 32

  aes = pyaes.AESModeOfOperationCTR(key[0:32])
  text = aes.decrypt(cipher)

  return str(text, encoding="utf-8")
