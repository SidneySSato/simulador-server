PIDTEMP=`ps -ef | grep  'simulador-server' | grep devaws | awk '{ print $2 }'`
echo 'kill [' $PIDTEMP ']'
kill $PIDTEMP
