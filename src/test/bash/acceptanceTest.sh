#!/bin/bash -e

output=$(curl http://localhost:8090/pokemonDetails/42)
expected='{"id":42,"name":"golbat","types":["poison","flying"],"timesMarkedFavorite":0}'
if [[ "$expected" != "$output" ]] ; then
	echo Expected: $expected, but is $output
	exit 1
fi


user_id=$(curl -X POST http://localhost:8090/users | grep -Po '"userId":"\K[^"]++')

curl --header "Content-Type: application/json" \
  --header "user_id:$user_id" \
  --request POST \
  --data '{"pokemonId":42}' \
  http://localhost:8090/users/favorite

output=$(curl http://localhost:8090/pokemonDetails/42)
expected='{"id":42,"name":"golbat","types":["poison","flying"],"timesMarkedFavorite":1}'
if [[ "$expected" != "$output" ]] ; then
	echo Expected: $expected, but is $output
	exit 1
fi
