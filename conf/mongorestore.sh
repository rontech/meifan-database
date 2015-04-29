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
  # backup
  mkdir -p dbackup
  dt=`date +"%Y%m%d%H%M%S"`
  mongodump -hlocalhost -o ./dbackup/$dt
  gzip -cr ./dbackup/$dt > ./dbackup/$dt.gz
  rm -rvf ./dbackup/$dt

  # restore
  mongorestore -hlocalhost ./sampledb --drop
  echo "OK."
else
  echo "Canceled."
fi
