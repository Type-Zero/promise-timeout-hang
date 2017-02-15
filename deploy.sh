#!/usr/bin/env bash
if [ -z $1 ]; then
    echo "need an S3 bucket"
    exit 1
fi
S3_BUCKET=$1

lein clean
lein cljs-lambda build
mkdir -p target/package
unzip -qq target/promise-timeout-hang/promise-timeout-hang.zip -d target/package
cp cljs-timeout.yml target/package

TMP_FILE=$(mktemp target/package/XXXXX)
aws cloudformation package --template-file target/package/cljs-timeout.yml --output-template-file ${TMP_FILE} --s3-bucket ${S3_BUCKET}
aws cloudformation deploy --template-file ${TMP_FILE} --stack-name cljs-timeout --capabilities CAPABILITY_IAM
rm ${TMP_FILE}
