package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" 
Transaction info representation
""")
    request {
        method 'GET'
        url value(consumer(regex('/api/trans/[0-9]{3}')),
                producer('/api/trans/123'))
    }

    response {
        status 200
        body([
                amount: regex("[1-9]{1,5}"),
                address: regex("[1-9]{1,5}")
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
