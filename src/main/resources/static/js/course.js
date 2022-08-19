 function alertSave(){
    alert("Are you sure to Save or Update?")
    };

    $(function(){
    fetchCourse();
    $(".block-id").hide();
    })
    var loc = window.location;
    var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "") + "/course/";

    function saveData(){
    event.preventDefault();
    let id = $("#id_course").val();
          let dataSerialized =  $("#saveCourse").serializeArray();
            dataSerialized.push({ name : "id", value : id });
         $.post({
         url: baseUrl + "add",
         data:dataSerialized,
         dataType : 'json',
         success : function(response){
            data = response.body;
            alert(response.message);
            fetchCourse();
            $('#saveCourse')[0].reset()
         },
         error : function(err){
             alert("error is " + err);
    }
    })
    };

    function fetchCourse(){
//    event.preventDefault();
 $(".block-id").hide();
    let tableInstance = $("#courseTable")
        let tableHead = "";
                    tableHead += `<thead><tr>
                    <th class = 'text-center'> S.N.</th>
                    <th> Course Id</th>
                    <th>  Name of Course</th>
                    <th> Full Marks</th>
                    <th> Edit</th>
                    <th> Delete</th>
                    </tr></thead>`;
            let tbody ="";
        $.post({
        url:  baseUrl + "courseList",
        success : function (response){
           let course = response.body;

           $.each(course, function(index, course){
                tbody += `<tr>
                <td>${index+1}</td>
                <td>${course.courseId}</td>
                 <td>${course.courseName}</td>
                 <td>${course.courseMarks}</td>
                 <td><button type="button" class="btn btn-warning" data-id="${course.id}" onclick="editCourse(this)">Edit </button></td>
                 <td><button type="button" class="btn btn-warning" data-id="${course.id}"  onclick="deleteCourse(this)">Delete </button></td>
                 </tr>`
           })
           },
            error : function(err) {
            						alert("error is" + err)
            					},
           complete : function(){
           let tableBody = "";
            tableBody = `<tbody>` + tbody + `</tbody>`;
             let table = tableHead + tableBody;
              tableInstance.html(table);
            }
        })
        }

        function editCourse(instance) {
        let id = $(instance).attr('data-id');
        		$.ajax({
        			type :"POST",
        			url : baseUrl + "edit",
        			data:{id},
        			dataType: 'json',
        			success: function(response) {
        			console.log(response)
        			let course = response.body;
        			$("#id_course").val(course.id),
        				$("#courseId").val(course.courseId),
        				 $("#courseName").val(course.courseName),
                            $("#courseMarks").val(course.courseMarks)
        				      },
        				      error : function(err) {
                              				alert("error is" + err)
                              			}
                              			})
                              			}

                              			function deleteCourse(instance){
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
                                                              fetchCourse();
                                                              },
                                                                error : function(err) {
                                                                	alert("error is" + err)
                                                                		}
                                             }
                                             )
                                             }
