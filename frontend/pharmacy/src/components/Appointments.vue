<template>
  <div>


   
<!-- Button trigger modal -->
<!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#appointmentModal">
  Launch demo modal
</button> -->

<!-- Modal -->
<div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="appointmentModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div v-if="selectedAppointment" class="modal-content">
      <div class="modal-header">
        <span v-bind:class="{ 'badge-info': selectedAppointment.type == 'COUNSELING', 'badge-primary': selectedAppointment.type == 'EXAMINATION' }" class="badge">{{selectedAppointment.type}}
        <span v-if="false" class="badge badge-danger">CALCELED</span>
        </span>
        <h5 class="modal-title" id="appointmentModalLabel">Appointment</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table">
          <tr>
            <th>{{selectedAppointment.type}}</th>
            <td>Type</td>
          </tr>
          <tr>
            <th>Doctor</th>
            <td>{{selectedAppointment.doctor.name}} {{selectedAppointment.doctor.surname}}</td>
          </tr>
          <tr>
            <th>Start Time</th>
            <td>{{UtilService.formatDateTime(selectedAppointment.startTime)}}</td>
          </tr>
          <tr>
            <th>Duration</th>
            <td>{{selectedAppointment.durationInMins}}min</td>
          </tr>
          <tr>
            <th>Price</th>
            <td>${{selectedAppointment.price}}</td>
          </tr>
        </table>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="bookAppointment(selectedAppointment)" >Confirm</button>
      </div>
    </div>
  </div>
</div>



    <div class="row">
      <h3>Appointments</h3>
      <p>{{filterPharmacyId}}</p>
    </div>
    <div class="row">
      <!-- <div class="col-md-4" v-for="a in appointments"> -->
      <div v-if="appointments" class="w-100" v-for="a in appointments">
        <div class="appointment card mb-4" v-if="!filterPharmacyId || a.pharmacy.id == filterPharmacyId">
          <div class="card-header">
            <h5>
              Free apointment at
              {{ UtilService.formatDateTime(a.startTime) }} ({{
                a.durationInMins
              }}min)
            </h5>
          </div>
          <div class="card-body text-left">
            <div class="row">
              <div class="col-sm-6 h5">
                <table>
                  <tr>
                    <td>Dermatologist:</td>
                    <td class="pl-5">
                      <strong>{{ a.doctor.name }}</strong>
                    </td>
                  </tr>
                  <tr>
                    <td>Pharmacy:</td>
                    <td class="pl-5">
                      <strong>{{ a.pharmacy.name }}</strong>
                    </td>
                  </tr>
                  <tr>
                    <td>Price per hour:</td>
                    <td class="pl-5">
                      <strong>${{ a.price }} USD</strong>
                    </td>
                  </tr>
                </table>
              </div>
              <div class="col-sm-6 h5">
                <table>
                  <tr>
                    <td>Starting at:</td>
                    <td class="pl-5">
                      <strong>{{
                        UtilService.formatDateTime(a.startTime)
                      }}</strong>
                    </td>
                  </tr>
                  <tr>
                    <td>Duration:</td>
                    <td class="pl-5">
                      <strong>{{ a.durationInMins }}min</strong>
                    </td>
                  </tr>
                </table>
                <br />
                <button
                  class="btn btn-block btn-primary"
                  v-bind:disabled="a.booked || this.user.role != 'PATIENT'"
                  v-on:click="bookAppointmentModal(a)"
                >
                  Book an appointment
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-else class="spinner-border" role="status">
        <span class="sr-only">Loading...</span>
      </div>
      <h4 v-if="appointments && appointments.length == 0">No appointments available</h4>
    </div>
  </div>
</template>

<script>
import AppointmentDataService from '../service/AppointmentDataService.js';
import UtilService from '@/service/UtilService.js';
import AuthService from '@/service/AuthService.js';

export default {
    setup() {
      return { UtilService }
    },

    name: 'Appointments',
    data() {
        return {
            appointments: null,
            selectedAppointment: null,
            userId: null,
            // limit: 0,
        };
    },
    props: ['limit', 'filterPharmacyId'],
    computed:{
      // medicinesSlice(){
      //   return this.limit ? this.medicines.slice(0,this.limit) : this.medicines
      // },
    },

    methods: {
      getDermatologAppointments() {
        AppointmentDataService.getFreeDermAppointments() // HARDCODED
              .then(response => {
                  this.appointments = response.data;
                  console.log(response.data);
              });
      },

      bookAppointmentModal(appointment) {
        this.selectedAppointment = appointment;
        $('#appointmentModal').modal();
      },

      bookAppointment(a) {
        // TODO: Getloggedin user id
        a.booked = true;

        AppointmentDataService.bookAppointment(a.id, this.userId).then(response => {
        });
      }
    },
    mounted() {
        this.getDermatologAppointments();
    },
    created() {
      this.user = this.user = AuthService.getCurrentUser();
      this.userId = AuthService.getLoggedIdOrLogout();

    }
}
</script>