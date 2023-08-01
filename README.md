# test-fabrick
Test API Fabrick

All the tasks related to the REST service have been implemented.

I have also completed the frontend part using Thymeleaf, HTML, Bootstrap, CSS, and written the transaction data through JPA repositories.

Access to the pages is ensured through in-memory authentication using the security module:

User: marco
Password: pass

The parameters provided in the specifications for the transfer operation appear to be incorrect or not up-to-date based on the API structure. The correct JSON for insertion should have the following specifications:
X-Time-Zone
accountId
creditor
creditor.name
creditor.account
creditor.account.accountCode
description
amount
currency
taxRelief.isCondoUpgrade
taxRelief.creditorFiscalCode
taxRelief.beneficiaryType
taxRelief.naturalPersonBeneficiary.fiscalCode1
taxRelief.legalPersonBeneficiary.fiscalCode

The implementation of the POST method has been done according to the provided specifications.

Testing API: cURL, Postman
