package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" 
User info representation
""")
    request {
        method 'GET'
        url value(consumer(regex('/api/user/[0-9]{3}')),
                producer('/api/user/123'))
    }

    response {
        status 200
        body([
                name: "Bob",
                email: "bob@mail.com"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
