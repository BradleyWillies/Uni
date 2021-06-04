@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Dashboard') }}</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    {{ __('Welcome ' . auth()->user()->organiser->name) }}
                        <a href="{{route('dashboard.event.create')}}" class="btn btn-primary float-right">{{ __('New Event') }}</a>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    @if (count($events) > 0)
                        <table border='1' style='border-collapse:collapse; text-align:center'>
                            <tr>
                                <th>Name</th><th>Description</th><th>Location</th><th>Date/Time</th><th>Interest Ranking</th>
                            </tr>

                            @foreach ($events as $event)
                                <tr>
                                    <td><a href="{{route("dashboard.event.show", $event->id)}}">{{Str::limit($event['name'], 50)}}</a></td>
                                    <td>{{Str::limit($event['description'], 50)}}</td>
                                    <td>{{Str::limit($event['location'], 50)}}</td>
                                    <td>{{$event['date_time']->format("d-m-Y H:i")}}</td>
                                    <td>{{$event['interest_ranking']}}</td>
                                </tr>
                            @endforeach
                        </table>
                    @endif
                </div>
            </div>

        </div>
    </div>
</div>
@endsection
