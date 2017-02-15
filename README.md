# promise-timeout-hang
This creates a Lambda functions for testing if they will hang after initial 
invocation when the function memory is 128 MB.

## Requirements to build
 * https://leiningen.org/
 * https://www.npmjs.com/

To build and deploy:

    ./deploy.sh <s3_bucket_name>

## Causing the hang
The function will stop responding if you invoke with a value that will cause a Lambda global timeout. Afterwards even if the event timeout is short enough, the function will not return again. If more memory is applied the function works again.

```
ARN=<function arn>

# Verify it is working
aws lambda invoke --function-name "${ARN}" --payload '{"timeout":1}' --query "LogResult" --log-type Tail --output text /dev/null | base64 -d

# Now break it
aws lambda invoke --function-name "${ARN}" --payload '{"timeout":6000}' --query "LogResult" --log-type Tail --output text /dev/null | base64 -d

#  Broken
aws lambda invoke --function-name "${ARN}" --payload '{"timeout":1}' --query "LogResult" --log-type Tail --output text /dev/null | base64 -d
```
