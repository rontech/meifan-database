#!/bin/bash

#MEIFAN_HOME=/home/jie-z/Work/meifan
#cd $MEIFAN_HOME/database/conf

# confirm
printf "WARNING:
This command will DELETE ALL DATA in databases.
and RELOAD the initial SAMPLE DATA. Are you sure? [y/n]: "

read answer

if [ $answer = "y" -o $answer = "Y" ]
then
  mongorestore -hlocalhost ./sampledb --drop
  echo "OK."
else
  echo "Canceled."
fi
