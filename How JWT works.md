# How JWT works
#### A JSON Web Token or JWT is made up of three parts:

- The header: contains some metadata about the token itself.
- The payload: contains the data that we want to encode into the token, so the more data we want to encode here the bigger is the JWT.
- The signature.
  
These first two parts, the header and the payload, are just plain text that will get encoded, but not encrypted.

So anyone will be able to decode them and read them, we cannot store any sensitive data in here. But that's not a problem at all because in the third part, the signature, is where things really get interesting. The signature is created using the header, the payload, and the secret that is saved on the server.

And this whole process is then called signing the Json Web Token. The signing algorithm takes the header, the payload, and the secret to create a unique signature. So only this data plus the secret can create this signature. Then together with the header and the payload, these signature forms the JWT, which then gets sent to the client.

![JWT schema](https://i.stack.imgur.com/bOHqZ.png)

Once the server receives a JWT to grant access to a protected route, it needs to verify it in order to determine if the user really is who he claims to be. In other words, it will verify if no one changed the header and the payload data of the token. So again, this verification step will check if no third party actually altered either the header or the payload of the Json Web Token.

So, how does this verification actually work? Well, it is actually quite straightforward. Once the JWT is received, the verification will take its header and payload, and together with the secret that is still saved on the server, basically create a test signature.

But the original signature that was generated when the JWT was first created is still in the token, right? And that's the key to this verification. Because now all we have to do is to compare the test signature with the original signature. And if the test signature is the same as the original signature, then it means that the payload and the header have not been modified. 

![JWT testing](https://i.stack.imgur.com/b2dzI.png)

Because if they had been modified, then the test signature would have to be different. Therefore in this case where there has been no alteration of the data, we can then authenticate the user. And of course, if the two signatures are actually different, well, then it means that someone tampered with the data. Usually by trying to change the payload. But that third party manipulating the payload does of course not have access to the secret, so they cannot sign the JWT. So the original signature will never correspond to the manipulated data. And therefore, the verification will always fail in this case. And that's the key to making this whole system work. It's the magic that makes JWT so simple, but also extremely powerful.

Now let's do some practices with nodejs:

Configuration file is perfect for storing JWT SECRET data. Using the standard HSA 256 encryption for the signature, the secret should at least be 32 characters long, but the longer the better.

config.env:
```env
JWT_SECRET = my-32-character-ultra-secure-and-ultra-long-secret
//after 90days JWT will no longer be valid, even the signuter is correct and everything is matched.
JWT_EXPIRES_IN=90
```