 function alertSave(){
    alert("Are you sure to Save or Update?")
    };
    $(function(){
    fetchStudent();
    $(".block-id").hide();
    })
    var loc = window.location;
    var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "") + "/student/";

    function saveData(){
    event.preventDefault();
    let id = $("#id_student").val();
          let dataSerialized =  $("#saveStudent").serializeArray();
            dataSerialized.push({ name : "id", value : id });
         $.post({
         url: baseUrl + "add",
         data:dataSerialized,
         dataType : 'json',
         success : function(response){
            data = response.body;
            alert(response.message);
            fetchStudent();
            $('#saveStudent')[0].reset()
         },
         error : function(err){
             alert("error is " + err);
    }
    })
    };

    function fetchStudent(){
//    event.preventDefault();
 $(".block-id").hide();
    let tableInstance = $("#studentTable")
        let tableHead = "";
                    tableHead += `<thead><tr>
                    <th class = 'text-center'> S.N.</th>
                    <th> Student Id</th>
                    <th>  Name of Student</th>
                    <th> Marks</th>
                    <th> Edit</th>
                    <th> Delete</th>
                    </tr></thead>`;
            let tbody ="";
        $.post({
        url:  baseUrl + "studentList",
        success : function (response){
           let  student = response.body;

           $.each(student, function(index, student){
                tbody += `<tr>
                <td>${index+1}</td>
                <td>${student.studentId}</td>
                 <td>${student.studentName}</td>
                 <td>${student.marks}
                 <td><button type="button" class="btn btn-warning" data-id="${student.id}" onclick="editStudent(this)">Edit </button></td>
                 <td><button type="button" class="btn btn-warning" data-id="${student.id}"  onclick="deleteStudent(this)">Delete </button></td>
                 </tr>`
           })
           },
            error : function(err) {
            						alert("error is" + err)
            					},
           complete : function(){
           let tableBody = "";
            tableBody = `<tbody>` + tbody + `</tbody>`
             let table = tableHead + tableBody;
              tableInstance.html(table);
            }
        })
        }

        function editStudent(instance) {
        let id = $(instance).attr('data-id');
        		$.ajax({
        			type :"POST",
        			url : baseUrl + "edit",
        			data:{id},
        			dataType: 'json',
        			success: function(response) {
        			let student = response.body;
        			$("#id_student").val(student.id),
        				$("#studentId").val(student.studentId),
        				 $("#studentName").val(student.studentName),
                           $("#marks").val(student.marks)
        				      },
        				      error : function(err) {
                              				alert("error is" + err)
                              			}
                              			})
                              			}

                              			function deleteStudent(instance){
//                                            event.preventDefault();
                                           let id = $(instance).attr('data-id');
                              			console.log(id, "id");
                                             $.ajax({
                                                  type:"POST",
                                                    url: baseUrl + "delete",
                                                    data:{id},
                                                        dataType: 'json',
                                                            success: function(response){
                                                              alert(response.message);
                                                              fetchStudent();
                                                              },
                                                                error : function(err) {
                                                                	alert("error is" + err)
                                                                		}
                                             }
                                             )
                                             }



