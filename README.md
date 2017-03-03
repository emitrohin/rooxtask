# Spring Boot 1.5 Demo project

[![Dependency Status](https://dependencyci.com/github/emitrohin/rooxtask/badge)](https://dependencyci.com/github/emitrohin/rooxtask)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4370293ceb2c4046ac3a41db99fcef1b)](https://www.codacy.com/app/emitrohin/rooxtask?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=emitrohin/rooxtask&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/emitrohin/rooxtask.svg?branch=master)](https://travis-ci.org/emitrohin/rooxtask)

## Run (from project directory)

    $ mvn spring-boot:run

In memory database is initialized each time you run it
Unauthorized access is prohibited

If you need jar then run

    $ mvn package

## Token

This application is based on custom token authorization. Header and token format is "Authorization: Bearer <id of customer>". For example, "Authorization: Bearer 100001"

## Customer handling

Read-only access. To access customer data you may use authorized customer id or @me literal

    curl 'http://localhost:8080/api/v1.0/customers/100001' -i -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me' -i -H'Authorization: Bearer 100001'

## Partner mappings handling

Supports CRUD operations

Get all

    curl 'http://localhost:8080/api/v1.0/customers/100001/partner-mappings' -i -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me/partner-mappings' -i -H'Authorization: Bearer 100001'

Get by id

    curl 'http://localhost:8080/api/v1.0/customers/100001/partner-mappings/100008' -i -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me/partner-mappings/100008' -i -H'Authorization: Bearer 100001'

Create new

    curl 'http://localhost:8080/api/v1.0/customers/100001/partner-mappings/' -i -d'{"partnerId":"newId","partnerCustomerId":"newId","lastName":"name","firstName":"name","middleName":"name","avatarImage":"AQABAAEAAQAB"}' -H'Content-Type: application/json' -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me/partner-mappings/' -i -d'{"partnerId":"newId","partnerCustomerId":"newId","lastName":"name","firstName":"name","middleName":"name","avatarImage":"AQABAAEAAQAB"}' -H'Content-Type: application/json' -H'Authorization: Bearer 100001'

Update

    curl 'http://localhost:8080/api/v1.0/customers/100001/partner-mappings/100008' -i -d'{"partnerId":"newId","partnerCustomerId":"newId","lastName":"name","firstName":"name","middleName":"name","avatarImage":"AQABAAEAAQAB"}' -X PUT -H'Content-Type: application/json' -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me/partner-mappings/100008' -i -d'{"partnerId":"newId","partnerCustomerId":"newId","lastName":"name","firstName":"name","middleName":"name","avatarImage":"AQABAAEAAQAB"}' -X PUT -H'Content-Type: application/json' -H'Authorization: Bearer 100001'
    
Delete

    curl 'http://localhost:8080/api/v1.0/customers/100001/partner-mappings/100008' -i -X DELETE -H'Authorization: Bearer 100001'
    curl 'http://localhost:8080/api/v1.0/customers/@me/partner-mappings/100008' -i -X DELETE -H'Authorization: Bearer 100001'
    
## Data format

Partner mappings

    {
        "partnerId": string,
        "partnerCustomerId": string,
        "lastName": string,
        "firstName": string,
        "middleName": string,
        "avatarImage": string that represents byte array (byte[])
    }