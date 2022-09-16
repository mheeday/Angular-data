import requests
import random
import names

randomData = {
    "firstName": "Ayo",
    "lastName": "Bello",
    "username": "Ayo_bello",
    "age": 12
}

for i in range(200):
    randomData["firstName"] = names.get_first_name()
    randomData["lastName"] = names.get_last_name()
    randomData["username"] = randomData["firstName"] + '_' + randomData["lastName"]
    randomData["age"] = random.randint(10, 80)

    requests.post("http://localhost:12345/user/create", json=randomData)