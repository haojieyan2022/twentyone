starter_project=$1
path="../src/main/java/"
if [ ! -d "$path/starter/$starter_project" ]; then
    echo "project directory $starter_project does not exist"
    ls -d $path/starter/*
    exit 1
fi
cd $path/
for starter_file in $(find "starter/"$starter_project -name "*.java"); do
    echo $starter_file
    file="${starter_file//starter\/"$starter_project"\//}"
    diff -ub $starter_file $file
done