# author: ourfor
# description: import data to mongodb
# date: 20191030

for i in `ls | grep '[.]json$'`;
do 
	echo $i | awk 'BEGIN{FS="."}{print $1}'|xargs -I % mongoimport -d test -c % --file %.json
done
