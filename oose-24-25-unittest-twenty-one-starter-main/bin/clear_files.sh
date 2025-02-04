starter_project=$1
src_path="../src/main/java"
test_path="../src/test/java"
clear_files(){
    path=$1
    prefix=$2
    for file in $(find $path -name "$prefix*.java"); do
        if [[ ! $file == *"starter"* ]]; then
            rm $file
        fi
    done
}

if [[ "debug"  == $starter_project ]]; then
    clear_files $test_path ""
fi
if [[ "unit_test"  == $starter_project ]]; then
    clear_files $test_path "Mock"
    clear_files $test_path "Fake"
fi
