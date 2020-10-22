#!/usr/bin/python3

import http.client, urllib.request, urllib.parse, urllib.error
import subprocess
import json
import sys

def loadLanguage( language, apiToken ):
    params = urllib.parse.urlencode({'api_token': apiToken, 'action': 'view_terms', 'id': '334421', 'type': 'json', 'language': language})
    headers = {"Content-type": "application/x-www-form-urlencoded",
                "Accept": "text/plain"}
    conn = http.client.HTTPSConnection("poeditor.com")
    conn.request("POST", "/api/", params, headers)
    response = conn.getresponse()
    data = response.read()
    conn.close()

    results = json.loads(data.decode('utf-8'))    # obj now contains a dict of the data

    if results['response']['code'] == '200':
        # Open file
        fo = open("src/main/resources/i18n/messages_"+language+".properties", "wb")

        lineUtf8 = (language + ": \n").encode('UTF-8')
        fo.write(lineUtf8)

        for translation in results['list']:
            if translation['definition']['form'] != "":
                val = translation['definition']['form']
                val = val.replace("\n", "\\n")
                lineUtf8 = ("    " + translation['term'] + ": " + val + "\n").encode('UTF-8')
                fo.write(lineUtf8)

        # Close opend file
        fo.close()
    else:
        print("poeditor API responded with error code: " + results['response']['code'])
        sys.exit(1)


def getApiToken():
    p1 = subprocess.Popen(["source ~/.zshrc; getPassword 'POEditor API Token'"], stdout=subprocess.PIPE, shell=True, executable='/bin/zsh')
    output, err = p1.communicate()
    if err:
        sys.stderr.write(err.decode("utf-8"))
    return output.decode("utf-8").splitlines()[-1].split()[0]

apiToken = getApiToken()
print('Using API token: ' + apiToken)

loadLanguage("sq", apiToken) # albanian
loadLanguage("bs", apiToken) # bosnian
loadLanguage("hr", apiToken) # croatian
loadLanguage("en", apiToken) # english
loadLanguage("fr", apiToken) # french
loadLanguage("de", apiToken) # german
loadLanguage("it", apiToken) # italian
loadLanguage("pt", apiToken) # portuguese
loadLanguage("rm", apiToken) # romansh
loadLanguage("sr", apiToken) # serbian
loadLanguage("es", apiToken) # spanish
loadLanguage("ti", apiToken) # tigrinya
loadLanguage("tr", apiToken) # turkish

print('Successfully loaded terms')
