<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Personal Meal Plan</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .autocomplete {
                position: relative;
                display: inline-block;
            }

            .autocomplete-items {
                position: absolute;
                border: 1px solid #d4d4d4;
                max-height: 150px;
                overflow-y: auto;
                z-index: 99;
                background-color: #f1f1f1;
                width: 100%;
            }

            .autocomplete-items div {
                padding: 10px;
                cursor: pointer;
                background-color: #fff;
                border-bottom: 1px solid #d4d4d4;
            }

            .autocomplete-items div:hover {
                background-color: #e9e9e9;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="mb-4">Create Personal Meal Plan</h1>
            <form action="personalMealPlans" method="post">
                <input type="hidden" name="action" value="create">

                <div class="form-group">
                    <label for="startDate">Start Date:</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                </div>

                <div class="form-group">
                    <label for="endDate">End Date:</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                </div>

                <!-- Meal Plan Details Section -->
                <div id="mealPlanDetailsContainer">
                    <h2 class="mt-4 mb-3">Meal Plan Details</h2>
                    <!-- Sample meal plan detail -->
                    <div class="mealPlanDetail" id="mealPlanDetail1">
                        <div class="form-group">
                            <label for="dayOfWeek">Day of Week:</label>
                            <select class="form-control" id="dayOfWeek" name="dayOfWeek" required>
                                <option value="Monday">Monday</option>
                                <option value="Tuesday">Tuesday</option>
                                <option value="Wednesday">Wednesday</option>
                                <option value="Thursday">Thursday</option>
                                <option value="Friday">Friday</option>
                                <option value="Saturday">Saturday</option>
                                <option value="Sunday">Sunday</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="mealName1">Meal Name:</label>
                            <div class="autocomplete">
                                <input type="text" id="mealNameInput1" class="form-control" name="mealName" oninput="filterMealNames('mealNameInput1', 'mealNameDropdown1')" autocomplete="off" required>
                                <div id="mealNameDropdown1" class="autocomplete-items"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="weeklyMenuID1">Weekly Menu ID:</label>
                            <input type="text" class="form-control" id="weeklyMenuID1" name="weeklyMenuID" readonly required>
                        </div>
                    </div>
                </div>

                <!-- Button to add another meal plan detail -->
                <button type="button" class="btn btn-secondary mb-3" id="addDetailButton" onclick="addMealPlanDetail()">Add Another Meal Plan Detail</button><br>

                <!-- Submit button -->
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
                    var mealNames = [
                        {id: '1', name: 'Vegetarian Delight'},
                        {id: '2', name: 'High Protein Plan'},
                        {id: '3', name: 'Low Carb Plan'},
                        {id: '4', name: 'Vegan Feast'},
                        {id: '5', name: 'Gluten-Free Menu'},
                        {id: '6', name: 'Keto Plan'},
                        {id: '7', name: 'Mediterranean Diet'},
                        {id: '8', name: 'Paleo Diet'},
                        {id: '9', name: 'Dairy-Free Menu'},
                        {id: '10', name: 'Balanced Diet'},
                        {id: '11', name: 'New Menu'},
                        {id: '12', name: 'Songoku'}
                    ];

                    function filterMealNames(inputId, dropdownId) {
                        var input = document.getElementById(inputId);
                        var dropdown = document.getElementById(dropdownId);
                        var filter = input.value.toLowerCase();

                        dropdown.innerHTML = '';

                        var filteredNames = mealNames.filter(function (meal) {
                            return meal.name.toLowerCase().includes(filter);
                        });

                        filteredNames.forEach(function (meal) {
                            var option = document.createElement('div');
                            option.textContent = meal.name;
                            option.addEventListener('click', function () {
                                selectMealName(inputId, dropdownId, meal.name, meal.id);
                            });
                            dropdown.appendChild(option);
                        });

                        dropdown.style.display = filteredNames.length > 0 ? 'block' : 'none';
                    }

                    function selectMealName(inputId, dropdownId, name, id) {
                        document.getElementById(inputId).value = name;
                        document.getElementById(dropdownId).style.display = 'none';
                        document.getElementById(inputId.replace('mealNameInput', 'weeklyMenuID')).value = id;
                    }

                    var detailCount = 1;

                    function addMealPlanDetail() {
                        var container = document.getElementById('mealPlanDetailsContainer');
                        var detailDiv = document.createElement('div');
                        detailDiv.classList.add('mealPlanDetail');
                        detailDiv.id = 'mealPlanDetail' + detailCount;

                        detailDiv.innerHTML = `
                    <div class="form-group">
                        <label for="dayOfWeek${detailCount}">Day of Week:</label>
                        <input type="text" class="form-control" id="dayOfWeek${detailCount}" name="dayOfWeek" required>
                    </div>
                    <div class="form-group">
                        <label for="mealName${detailCount}">Meal Name:</label>
                        <div class="autocomplete">
                            <input type="text" id="mealNameInput${detailCount}" class="form-control" name="mealName" oninput="filterMealNames('mealNameInput${detailCount}', 'mealNameDropdown${detailCount}')" autocomplete="off" required>
                            <div id="mealNameDropdown${detailCount}" class="autocomplete-items"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="weeklyMenuID${detailCount}">Weekly Menu ID:</label>
                        <input type="text" class="form-control" id="weeklyMenuID${detailCount}" name="weeklyMenuID" readonly required>
                    </div>
                `;
                        container.appendChild(detailDiv);

                        // Increment detailCount
                        detailCount++;

                        // Disable the button after adding one detail
                        document.getElementById('addDetailButton').disabled = true;
                    }
        </script>
    </body>
</html>
