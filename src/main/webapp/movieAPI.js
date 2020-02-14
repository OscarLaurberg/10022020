
        function getAllMovies(evt) {
            (fetch("https://localhost:8080/rest-jpa-devops-starter/api/movie/all")
                    .then(res => res.json()) //in flow1, just do it
                    .then(data => {
                        //console.log("data", data);
                        let tableString = "<table> <tr> <th>Title</th><th>Year</th></tr>";
                        let userTableArray = data.map(data => data = `<tr><td>${data.name} </td><td>${data.year} </td></tr>`);
                        userTableArray.forEach(data => {
                            tableString += data;
                        });
                        tableString += "</table> </tr>";
                        document.getElementById("movieResult").innerHTML = tableString;
                        console.log(tableString);

                    }));


        }
        document.getElementById('getAllMovies').addEventListener('click',getAllMovies);