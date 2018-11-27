# Mobile-Coding-Challenge using java Android
In this repository you will find a source code of a small app that will list the most starred Github repos that were created in the last 30 days  (relative to 2017-11-22) using Github API.

Achieved Features

List the most starred Github repos that were created in the last 30 days.
List the results. One repository per row.

for each repo/row the following details :
Repository name
Repository description
Numbers of stars for the repo.
Username and avatar of the owner.
[BONUS]  keep scrolling and new results should appear (pagination).

some important library used in this app :

'com.squareup.retrofit2:retrofit:2.1.0' that is a REST Client for Android and Java by Square. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.

for listing Repositories in custom layout we used 'com.android.support:cardview-v7:27.0.2' with 'com.android.support:recyclerview-v7:27.0.2', to manipulate complexe lists.

To launch the apk please go to ==> Mobile-Coding-Challenge\app\release\app-release.apk

Note : All repositories retrieved from the Github api are relative to the last 30 days of 2017-11-22.
