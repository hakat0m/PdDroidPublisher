## Eclipse project generator script

# generate eclipse project for all dependencies

cd pd-for-android
bash ../gradlew generateEclipseDependencies

# fix native library location in pd-core project

cd aarDependencies/org.puredata.android-pd-core-1.0.0-rc4
mv jni/* libs/
cd ../..

# clean unwanted eclipse files for current dummy project.

rm .project
rm .classpath
rm project.properties

cd ..

# generate eclipse project for ppp modules

bash gradlew eclipse

# After that, you can import all eclipse project in your workspace.