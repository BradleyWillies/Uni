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
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    @if (session()->has('events'))
                        <?php $events = session()->get('events'); ?>
                        <table border='1' style='border-collapse:collapse; text-align:center'>
                            <tr>
                                <th>Name</th><th>Description</th><th>Location</th><th>Date/Time</th><th>Interest Ranking</th>
                            </tr>

                            <?php foreach ($events as $event) { ?>
                                <tr>
                                    <td><?= $event['name'] ?></td>
                                    <td><?= $event['description'] ?></td>
                                    <td><?= $event['location'] ?></td>
                                    <td><?= $event['date_time'] ?></td>
                                    <td><?= $event['interest_ranking'] ?></td>
                                </tr>
                            <?php } ?>
                        </table>
                    @endif
                </div>
            </div>

        </div>
    </div>
</div>
@endsection
