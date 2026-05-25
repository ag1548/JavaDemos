run:
	@javac Main.java
	@# java Main
	@# script -q -c "java Main" output.txt
	@script -q -c "java Main" output2.txt

test:

	mkdir -p test_target

	javac -d test_target \
		-cp tests/junit-platform-console-standalone-6.1.0.jar \
		*.java

	java -jar ./tests/junit-platform-console-standalone-6.1.0.jar \
		execute \
		--class-path test_target \
		--scan-class-path \
		--details tree \
		--details-theme ascii \
		--disable-ansi-colors \
		| tee test_output.txt
