export const oktaConfig = {
    clientId: '0oa91wp8dopyNe9Fv5d7',
    issuer: 'https://dev-10835870.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}
