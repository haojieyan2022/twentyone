starter_project=$1
games_csv=$2
src_path="../src/main/java"
test_path="../src/test/java"

if [[ `pwd` == *"template"* ]]; then
    echo "Don't delete in template" 
    exit 1
fi

if [ ! -d "$src_path/starter/$starter_project" ]; then
    echo "project directory $starter_project does not exist"
    ls -d $src_path/starter/*
    exit 1
fi

./clear_games.sh $games_csv
./clear_files.sh $starter_project
for starter_file in $(find $src_path"/starter/"$starter_project -name "*.md"); do
    cat $starter_file >> ../ReadMe.md
done
`rm src\main\resources\card*csv`
`rm -rf "$src_path"/starter`
`rm -rf "$test_path"/starter`