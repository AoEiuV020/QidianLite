
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt


#各种问题通通无视
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-ignorewarnings
