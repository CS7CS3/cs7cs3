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
