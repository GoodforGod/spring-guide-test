package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" 
User info representation
""")
    request {
        method 'POST'
        url '/api/user'
        body(
                name: "Bob",
                email: "bob@mail.com"
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status 200
        body(
                name: "Bob",
                email: "bob@mail.com"
        )
        headers {
            contentType(applicationJson())
        }
    }
}
