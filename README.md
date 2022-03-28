# Journey Sharing

## API

visit https://documenter.getpostman.com/view/5965087/UVkiTeEB

## Run

``` bash
./run.sh
```

## TODO

- [ ] force a person to exit from a journey
- [ ] journey end (how?)
  - [ ] on expire (20 min)
  - [ ] user click & optional(arrive a certain location)
  - [ ] failover when host exits (yue yu)
    - [ ] if empty, cancel journey (yue yu)

## DONE

02/27/2022
- [x] structure update
  - for all requests/responses, create a class in directory entities/messages
    - private constrcutor, use make to create object
    - for empty responses, make always returns null
  - all requests use POST

- [x] request test() function

02/13/2022

- [x] join journey
- [x] exit journey
- [x] rate a user

02/10/2022

- [x] create journey
- [x] get journey
  - [x] get journey by id
  - [x] get journey by location (from where to where)



/tokens √
- 查看token是否过期

/tokens/refresh √
- 刷新token

/tokens/info √
- 返回token对应用户信息


1. 拿到一个token字符串
2. 使用base64解码字符串 -> byte[] (解密前的)
3. 对byte数组使用decrypt -> byte[] (解密后的)
4. 把byte数组转换成string `new String(byte数组)`
5. Token.fromJson(str) -> Token对象


1. 有一个token对象
2. 使用token.toJson() -> String 它是一个json字符串
3. 使用jsonStr.getBytes()拿到一个byte[]
4. encrypt加密这个字节数组，得到一个加密后的字节数组 
5. 使用base64编码Base64.encodeBase64String(加密后的字节数组);